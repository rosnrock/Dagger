package ru.otus.daggerhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.otus.daggerhomework.dagger.DaggerFragmentProducerComponent
import ru.otus.daggerhomework.dagger.FragmentProducerComponent
import javax.inject.Inject

class FragmentProducer : Fragment() {

    lateinit var fragmentComponent: FragmentProducerComponent

    @ru.otus.daggerhomework.dagger.Fragment
    @Inject
    lateinit var viewModelProducer: ViewModelProducer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentComponent = DaggerFragmentProducerComponent.builder()
            .activity((requireActivity() as MainActivity).mainActivityComponent).fragment(this)
            .build()
        fragmentComponent.inject(this)
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            viewModelProducer.generateColor()
        }
    }
}